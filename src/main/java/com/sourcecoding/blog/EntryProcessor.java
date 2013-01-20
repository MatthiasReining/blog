/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 *
 * @author mre
 */
@Named
public class EntryProcessor {

    @PostConstruct
    private void createDummyData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        entries = new ArrayList<>();

        String htmlContent = "<p>Im Prinzip bin ich mit blogger.com zufrieden. Allerdings reizt natürlich auch mal wieder ein neues System...</p>\n"
                + "  <p><img src=\"http://polpix.sueddeutsche.com/bild/1.1575007.1358355058/860x860/pep-guardiola.jpg\" /></p>\n"
                + "  <p>Vermutlich werde ich meinen Blog umziehen und technologisch auf jekyllbootstrap (http://jekyllbootstrap.com/) umstellen. Mit dem Kommentarsystem von disqus (http://disqus.com) schaut das Ganze eigentlich ganz nett aus :-)</p>  \n"
                + "  <p>Sobald ich hier technisch dazu gekommen bin, werde ich natürlich hier darüber berichten.</p>  \n";
        String subject = "Blogsystem";
        Entry e = new Entry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setCreated(sdf.parse("2013-01-20"));
        entries.add(e);

        htmlContent = "<p>\n" +
"  Im Prinzip bin ich mit blogger.com zufrieden. Allerdings reizt natürlich auch mal wieder ein neues System...\n" +
"  \n" +
"  <a href=\"#\">[weiterlesen]</a>\n" +
"  </p>";
        subject = "Blogsystem 2";
        e = new Entry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setCreated(sdf.parse("2012-05-20"));
        entries.add(e);

        htmlContent = "<p>Der jüngste Ausbruch der Gewalt im Zentrum und im Norden Malis zwingt immer mehr Menschen in die Flucht. Die Vereinten Nationen gehen davon aus, dass durch die Kümpfe zwischen islamistischen Rebellen und der Armee, in die seit Freitag vergangener Woche franzüsische Truppen eingreifen, etwa 30.000 Münner, Frauen und Kinder innerhalb des Landes vertrieben worden sind.\n"
                + "  \n"
                + "      <a href=\"#\">[weiterlesen]</a>\n"
                + "  </p>";
        subject = "SPD stürzt mit Steinbrück auf neues Tief";
        e = new Entry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setCreated(sdf.parse("2012-05-20"));
        entries.add(e);

    }
    public List<Entry> entries;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
