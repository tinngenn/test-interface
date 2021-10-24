<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <xsl:apply-templates select="产品信息"/>
    </xsl:template>
    <xsl:template match="产品信息">
        <html>
            <body>
                <table border="1px" align="center" width="400px" cellspacing="0px"
                       cellpadding="5px">
                    <tr align="center">
                        <th>产品编号</th>
                        <th>产品名称</th>
                        <th>产品价格</th>
                        <th>产品数量</th>
                    </tr>
                    <xsl:apply-templates select="产品"/>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="产品">
        <tr align="center">
            <td><xsl:value-of select="@产品编号"/></td>
            <td><xsl:value-of select="名称"/></td>
            <td><xsl:value-of select="价格"/></td>
            <td><xsl:value-of select="数量"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>