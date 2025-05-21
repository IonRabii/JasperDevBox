package io.neo.elk.helper;

import net.sf.jasperreports.engine.JRVirtualizable;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;

import java.io.IOException;

public class DebugVirtualizer extends JRFileVirtualizer {
    public DebugVirtualizer(int maxSize, String directory)
    {
        super(maxSize, directory);
    }

    @Override
    protected void pageOut(JRVirtualizable o) throws IOException
    {
        System.out.println("pageOut: " + o.hashCode());
        super.pageOut(o);
    }

    @Override
    protected void pageIn(JRVirtualizable o) throws IOException
    {
        System.out.println("pageIn: " + o.hashCode());
        super.pageIn(o);
    }

    @Override
    public void touch(JRVirtualizable o)
    {
        System.out.println("touch: " + o.hashCode());
        super.touch(o);
    }
}

