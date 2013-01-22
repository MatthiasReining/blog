/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourcecoding.blog;

import com.sourcecoding.blog.model.BlogEntry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.pegdown.PegDownProcessor;

/**
 *
 * @author mre
 */
@Named
@ApplicationScoped
public class EntryProcessor {

    private List<BlogEntry> entries;
    private Map<String, BlogEntry> entryMap;

    @PostConstruct
    void init() throws ParseException {
        System.out.println("in init");
        entries = new ArrayList<>();

        createDummyData();

        mdIn();

        Collections.sort(entries);

        entryMap = new HashMap<>();
        for (BlogEntry entry : entries) {
            entryMap.put(entry.getKey(), entry);
        }

    }

    public BlogEntry getEntryByKey(String key) {
        return entryMap.get(key);
    }

    void mdIn() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        PegDownProcessor pdp = new PegDownProcessor();
        Properties p = new Properties();

        try {
            BlogEntry be = new BlogEntry();


            FileReader fr = new FileReader("D:\\labs\\blog\\src\\test\\resources\\file1.md");
            BufferedReader br = new BufferedReader(fr);
            String strLine;
            //Read File Line By Line
            boolean collectMetaInfo = true;
            StringBuilder content = new StringBuilder();
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console

                if (strLine.isEmpty()) {
                    collectMetaInfo = false;
                }

                if (collectMetaInfo) {
                    //subject
                    if (strLine.startsWith("title")) {
                        be.setTitle(getValueFromPropertyLine(strLine, "title"));
                    }
                    if (strLine.startsWith("subtitle")) {
                        be.setSubtitle(getValueFromPropertyLine(strLine, "subtitle"));
                    }
                    if (strLine.startsWith("created")) {
                        String dateText = getValueFromPropertyLine(strLine, "created");
                        be.setCreated(sdf.parse(dateText));
                    }
                    //if (strLine.startsWith("title")) be.setTitle( getValueFromPropertyLine(strLine, "title"));

                } else {
                    content.append(strLine).append("\n");
                }
            }
            String htmlContent = pdp.markdownToHtml(content.toString());
            be.setHtmlContent(htmlContent);

            entries.add(be);

        } catch (IOException | ParseException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

    private String getValueFromPropertyLine(String line, String key) {
        if (line.startsWith(key)) {
            String value = new String(line.substring(key.length() + 1));
            return value;
        }
        return null;
    }

    private void createDummyData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        String htmlContent = "<p>Im Prinzip bin ich mit blogger.com zufrieden. Allerdings reizt natürlich auch mal wieder ein neues System...</p>\n"
                + "  <p><img src=\"http://polpix.sueddeutsche.com/bild/1.1575007.1358355058/860x860/pep-guardiola.jpg\" /></p>\n"
                + "  <p>Vermutlich werde ich meinen Blog umziehen und technologisch auf jekyllbootstrap (http://jekyllbootstrap.com/) umstellen. Mit dem Kommentarsystem von disqus (http://disqus.com) schaut das Ganze eigentlich ganz nett aus :-)</p>  \n"
                + "  <p>Sobald ich hier technisch dazu gekommen bin, werde ich natürlich hier darüber berichten.</p>  \n";
        String subject = "Blogsystem";
        String subTitle = "";
        BlogEntry e = new BlogEntry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setSubtitle(subTitle);
        e.setCreated(sdf.parse("2013-01-20"));
        entries.add(e);

        htmlContent = "<p>126.000 Atomfässer lagern dort, wo niemals radioaktiver Abfall hätte gelagert werden dürfen: im Salzbergwerk Asse II. Die Suche nach einer Lösung für das Desaster ist ein Wettlauf gegen die Zeit. Dennoch kann die Bergung womöglich erst 2036 beginnen.</p>"
                + "<p><img src=\"http://polpix.sueddeutsche.com/bild/1.1548904.1358712168/640x360/atommuelllager-asse.jpg\" /></p>"
                + "<p>Das Zeichen des guten Willens ist mitten im Wald zu besichtigen, über dem alten Salzbergwerk Asse II: Zu Beginn des Winters haben dort die Vorbereitungen für einen neuen Bohrplatz begonnen. Gut 500 Meter geht man von diesem Platz im Wald zur Hauptanlage der Asse mit ihrem gut hundert Jahre alten Schacht. Er ist bisher der einzige Zugang zu dem maroden Bergwerk, in dem etwa 126.000 Atomfässer lagern.</p>"
                + "<p>Die Bauleute richten jetzt den Zufahrtsweg zum Bohrplatz her, ein festes Fundament mit einer Stahlarmierung muss im Wald gegossen werden. Dieses Fundament soll stark genug sein, um das gewaltige Bohrgerät zu tragen. Niemand kann genau sagen, wann die erste Erkundungsbohrung beginnen kann.</p>"
                + "<p>Aber irgendwann in den nächsten Monaten könnte dort ein 17 Meter hoher Bohrturm das sichtbare Zeichen sein, dass es vorangeht. Dass es vielleicht doch eine Lösung geben könnte für den größten Atomskandal der Bundesrepublik. Dass es etwas werden könnte mit der Bergung des Atommülls aus bis zu 750 Metern Tiefe. Denn an dieser Stelle im Wald sollen die Fässer wieder ans Tageslicht kommen, um sicher neu verpackt zu werden und später auf eine verantwortbare Art anderswo wieder unter der Erde gelagert zu werden.</p>";
        subject = "Im Eiltempo gegen den Einsturz";
        subTitle = "Atommüll im Salzbergwerk Asse";
        e = new BlogEntry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setSubtitle(subTitle);
        e.setCreated(sdf.parse("2012-05-20"));
        entries.add(e);

        htmlContent = "<p>Der jüngste Ausbruch der Gewalt im Zentrum und im Norden Malis zwingt immer mehr Menschen in die Flucht. Die Vereinten Nationen gehen davon aus, dass durch die Kümpfe zwischen islamistischen Rebellen und der Armee, in die seit Freitag vergangener Woche franzüsische Truppen eingreifen, etwa 30.000 Münner, Frauen und Kinder innerhalb des Landes vertrieben worden sind.</p>";
        subject = "SPD stürzt mit Steinbrück auf neues Tief";
        e = new BlogEntry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setSubtitle("");
        e.setCreated(sdf.parse("2012-05-20"));
        entries.add(e);


        htmlContent = "<p>Doch kein Rücktritt? Mehrere Nachrichtenagenturen melden, FDP-Parteichef Philipp Rösler bleibe im Amt - trotz eines Rücktrittsangebots +++ Spitzenkandidat für die Bundestagswahl soll jedoch Fraktionschef Rainer Brüderle werden +++ McAllister verzichtet auf Posten des Oppositionsführers +++ SPD feiert Steinbrück und Weil +++</p>";
        subject = "Rösler soll FDP-Chef bleiben - Brüderle Spitzenkandidat";
        subTitle = "Nach der Niedersachsen-Wahl";
        e = new BlogEntry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setSubtitle(subTitle);
        e.setCreated(sdf.parse("2013-01-21"));
        entries.add(e);


        htmlContent = "<p>Der Deutsche Wetterdienst erwartet auch am Montag in weiten Teilen Deutschlands Behinderungen durch glatte Fahrbahnen. An mehreren deutschen Flughäfen ist mit massiven Verspätungen und Flugausfällen zu rechnen.</p>";
        subject = "Wetterdienst warnt vor Straßenglätte - Hunderte Flüge gestrichen";
        subTitle = "Blitzeis und Schnee";
        e = new BlogEntry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setSubtitle(subTitle);
        e.setCreated(sdf.parse("2012-05-20"));
        entries.add(e);

        htmlContent = "<p>In Brüssel setzt sich Bayerns früherer Ministerpräsident Stoiber unverhohlen für den bayerischen Schnupftabakhersteller Pöschl ein. Kritiker bemängeln, er missbrauche sein Amt als Entbürokratisierer, um Lobbyarbeit für die Tabakindustrie zu betreiben.</p>";
        subject = "Bürokratieabbau in Brüssel";
        subTitle = "Blitzeis und Schnee";
        e = new BlogEntry();
        e.setHtmlContent(htmlContent);
        e.setTitle(subject);
        e.setSubtitle(subTitle);
        e.setCreated(sdf.parse("2013-01-21"));
        entries.add(e);

    }

    public List<BlogEntry> getEntries() {
        return entries;
    }
}
