<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:key name="item-by-journal" match="item[@category='RefJournal']" use="journal"/>
    <xsl:key name="item-by-journalPublisher" match="item[@category='RefJournal']" use="publisher"/>
    <xsl:key name="item-by-venue" match="item[@category='RefConference']" use="venue"/>
    <xsl:key name="item-by-location" match="item[@category='RefConference']" use="location"/>
    <xsl:key name="item-by-book" match="item[@category='RefBookChapter']" use="book"/>
    <xsl:key name="item-by-bookPublisher" match="item[@category='RefBookChapter']" use="publisher"/>
    <xsl:template match="/bibliography">
        <html>
            <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
                <h1>Bibliography summary</h1>
                <h2>Journal articles</h2>
                <ul>
                    <li>Number of journal articles</li>
                    <dd>
                        <xsl:value-of select="count(item/journal) "/>
                    </dd>

                    <li>Most frequent journal</li>
                    <dd>

                        <xsl:for-each
                                select="item[@category='RefJournal'][count(. | key('item-by-journal', journal)[1]) = 1]">
                            <xsl:sort select="count(key('item-by-journal', journal))" data-type="number"
                                      order="descending"/>
                            <xsl:if test="position() = 1">
                                <xsl:value-of select="journal"/>
                            </xsl:if>
                        </xsl:for-each>

                    </dd>

                    <li>Most frequent Jorunal publisher</li>
                    <dd>
                        <xsl:for-each
                                select="item[@category='RefJournal'][count(. | key('item-by-journalPublisher', publisher)[1]) = 1]">
                            <xsl:sort select="count(key('item-by-journalPublisher', publisher))" data-type="number"
                                      order="descending"/>
                            <xsl:if test="position() = 1">
                                <xsl:value-of select="publisher"/>
                            </xsl:if>
                        </xsl:for-each>
                    </dd>
                </ul>
                <h2>Conference proceedings</h2>
                <ul>
                    <li>Number of conference proceedings</li>
                    <dd>
                        <xsl:value-of select="count(item/venue)"/>
                    </dd>

                    <li>Most frequent venue</li>
                    <dd>
                        <xsl:for-each
                                select="item[@category='RefConference'][count(. | key('item-by-venue', venue)[1]) = 1]">
                            <xsl:sort select="count(key('item-by-venue', venue))" data-type="number"
                                      order="descending"/>
                            <xsl:if test="position() = 1">
                                <xsl:value-of select="venue"/>
                            </xsl:if>
                        </xsl:for-each>
                    </dd>

                    <li>Most frequent location</li>
                    <dd>
                        <xsl:for-each
                                select="item[@category='RefConference'][count(. | key('item-by-location', location)[1]) = 1]">
                            <xsl:sort select="count(key('item-by-location', location))" data-type="number"
                                      order="descending"/>
                            <xsl:if test="position() = 1">
                                <xsl:value-of select="location"/>
                            </xsl:if>
                        </xsl:for-each>
                    </dd>
                </ul>
                <h2>Book chapters</h2>
                <ul>
                    <li>Number of book chapters</li>
                    <dd>
                        <xsl:value-of select="count(item/book) "/>
                    </dd>

                    <li>Most frequent book</li>
                    <dd>
                        <xsl:for-each
                                select="item[@category='RefBookChapter'][count(. | key('item-by-book', book)[1]) = 1]">
                            <xsl:sort select="count(key('item-by-book', book))" data-type="number"
                                      order="descending"/>
                            <xsl:if test="position() = 1">
                                <xsl:value-of select="book"/>
                            </xsl:if>
                        </xsl:for-each>
                    </dd>

                    <li>Most frequent Book publisher</li>
                    <dd>
                        <xsl:for-each
                                select="item[@category='RefBookChapter'][count(. | key('item-by-bookPublisher', publisher)[1]) = 1]">
                            <xsl:sort select="count(key('item-by-bookPublisher', venue))" data-type="number"
                                      order="descending"/>
                            <xsl:if test="position() = 1">
                                <xsl:value-of select="publisher"/>
                            </xsl:if>
                        </xsl:for-each>
                    </dd>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>