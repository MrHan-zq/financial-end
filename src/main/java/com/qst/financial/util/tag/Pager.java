package com.qst.financial.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author qst
 * @param <T>
 * 
 * @类说明：页面分页tag实体类
 */
public class Pager<T> extends TagSupport {

    private static final long serialVersionUID = -2613705016796991725L;
    
    private Integer curPage;
    
    private Integer totalPage;
    
    private Integer pageSize = 10;// 后续系统参数配置
    
    private Integer totalCount = 0;
    
    public void setCurPage(Integer curPage)
    {
        this.curPage = curPage;
    }
    
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public void setTotalPage(Integer totalPage)
    {
        this.totalPage = totalPage;
    }
    
    public Integer getTotalCount()
    {
        return totalCount;
    }
    
    public void setTotalCount(Integer totalCount)
    {
        this.totalCount = totalCount;
    }
    
    public int doStartTag()
        throws JspException
    {
        
        JspWriter out = pageContext.getOut();
        
        int pageNumber = 0;
        if (pageSize == 0)
        {
            pageSize = 10;
        }
        if (totalPage % pageSize == 0)
        {
            pageNumber = totalPage / pageSize;
        }
        else
        {
            pageNumber = (totalPage / pageSize) + 1;
        }
        if (curPage < 1)
        {
            curPage = 1;
        }
        try
        {
            if (pageNumber > 0)
            {
                out.print("<div class=\"row\"><div class=\"col-sm-6\"><div class=\"dataTables_info\" id=\"sample-table-2_info\">总页数:" + totalPage +"  当前页:" + curPage + "  总计数:" + totalCount + "</div></div><div class=\"col-sm-6\"><div class=\"dataTables_paginate paging_bootstrap\"><ul class=\"pagination\">");
                int start = 1;
                int end = totalPage;
                for (int i = 4; i >= 1; i--)
                {
                    if ((curPage - i) >= 1)
                    {
                        start = curPage - i;
                        break;
                    }
                }
                for (int i = 4; i >= 1; i--)
                {
                    if ((curPage + i) <= totalPage)
                    {
                        end = curPage + i;
                        break;
                    }
                }
                // 如果小于9则右侧补齐
                if (end - start + 1 <= 9)
                {
                    Integer padLen = 9 - (end - start + 1);
                    for (int i = padLen; i >= 1; i--)
                    {
                        if ((end + i) <= totalPage)
                        {
                            end = end + i;
                            break;
                        }
                    }
                }
                // 如果还小于9左侧补齐
                if (end - start + 1 <= 9)
                {
                    Integer padLen = 9 - (end - start + 1);
                    for (int i = padLen; i >= 1; i--)
                    {
                        if ((start - i) >= 1)
                        {
                            start = start - i;
                            break;
                        }
                    }
                }
                if (curPage > 1)
                {
                    if (start >= 1)
                    {
                        out.print("<li><a href='javascript:goPage(1)' class=\"pagebtn\">首页</a></li>");
                    }
                    out.print("<li><a href='javascript:goPage(" + (curPage - 1) + ")' class=\"pagebtn\">上一页</a></li>");
                }
                for (int i = start; i <= end; i++)
                {
                    if (i == curPage)
                    {
                        if (curPage != end)
                        {
                            out.print("<li class=\"active\"><a href='javascript:void(0);'>" + i + "</a></li>");
                        }
                        else if (i == end && i != 1)
                        {
                            out.print("<li class=\"active\"><a href='javascript:void(0);'>" + i + "</a></li>");
                        }
                    }
                    else
                    {
                        if (i == end)
                        {
                            out.print("<li><a href='javascript:goPage(" + i + ")'>" + i + "</a></li>");
                        }
                        else
                        {
                            out.print("<li><a href='javascript:goPage(" + i + ")'>" + i + "</a></li>");
                        }
                    }
                }
                if (curPage < totalPage)
                {
                    out.print("<li><a href='javascript:goPage(" + (curPage + 1) + ")'>下一页</a></li>");
                    if (end <= totalPage)
                    {
                        out.print("<li><a href='javascript:goPage(" + totalPage + ")'>尾页</a></li>");
                    }
                }
                if (end > start)
                {
                    out.print("</ul></div></div></div>");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
    
    public static Integer getStartIndex(Integer pageNum, Integer pageSize)
    {
        Integer res = 0;
        if (pageNum > 0)
        {
            res = (pageNum - 1) * pageSize;
        }
        return res;
    }
    
}
