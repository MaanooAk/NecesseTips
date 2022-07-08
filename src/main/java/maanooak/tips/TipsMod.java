package maanooak.tips;

import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import necesse.engine.commands.CommandsManager;
import necesse.engine.localization.message.LocalMessage;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.gfx.forms.presets.containerComponent.mob.ElderContainerForm;


@ModEntry
public class TipsMod {

    public void postInit() {
        CommandsManager.registerClientCommand(new TipChatCommand());

        for (int i = 1; i <= ElderContainerForm.TOTAL_TIPS; i++) {
            Tips.add(new LocalMessage("ui", "eldertip" + i).translate());
        }
        for (final String i : new String[] { "eldercraftingtip", "elderexploringtip", "eldertradingtip",
                "elderobjectstip", "eldertilestip", "eldertoolstip", "elderweaponstip", "elderarmortip",
                "eldertrinketstip", "elderenchantstip", "elderpotionstip", "eldersettlementstip" }) {
            Tips.add(new LocalMessage("ui", i).translate());
        }

        loadTipsFromUrl("https://necessewiki.com/Guide:Tips");

        System.out.println("Loaded tips: " + Tips.all().size());
    }

    private void loadTipsFromUrl(final String url) {
        try {
            final Scanner scanner = new Scanner(new URL(url).openStream(), "UTF-8");
            scanner.useDelimiter("\\A");

            final String html = scanner.next();
            loadTipsFromHtml(html);

            scanner.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTipsFromHtml(final String html) {

        final Pattern pattern = Pattern.compile("(?<=<li>)[^\\t]*?(?=<\\/li>)");
        final Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            final String text = matcher.group().replaceAll("(<[^>]*>|&#[^;]*;)", "").trim();
            if (text.chars().filter(i -> i == ' ').count() > 1) {
                Tips.add(text);
            }
        }
    }

}
