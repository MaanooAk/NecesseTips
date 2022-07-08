package maanooak.tips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import necesse.engine.GameLoadingScreen;
import necesse.engine.Screen;
import necesse.engine.util.GameUtils;
import necesse.gfx.GameBackground;
import necesse.gfx.gameFont.FontOptions;
import necesse.gfx.gameFont.GameFontHandler;


public final class Tips {

    private static HashSet<String> tips = new HashSet<String>();
    private static ArrayList<String> left = new ArrayList<String>();

    private static long lastNext = System.currentTimeMillis();
    private static long peekInterval = 10 /* seconds */ * 1000;

    private Tips() {}

    public static void add(String tip) {
        tips.add(tip);
//        System.out.println("Tip: " + tip);
    }

    public static Collection<String> all() {
        return tips;
    }

    public static String peek() {
        if (System.currentTimeMillis() - lastNext > peekInterval) {
            next();
        }
        refill();
        return left.get(left.size() - 1);
    }

    public static String next() {
        lastNext = System.currentTimeMillis();
        refill();
        return left.remove(left.size() - 1);
    }

    private static void refill() {
        if (!left.isEmpty()) return;
        left.addAll(tips);
        Collections.shuffle(left);
    }

    private static FontOptions fontOptions = new FontOptions(15).outline();

    public static void draw() {
        final String tip = Tips.peek();

        final GameBackground background = GameBackground.itemTooltip;
        final GameFontHandler font = GameLoadingScreen.font;

        final int x = 10;
        final int w = Screen.getHudWidth() * 75 / 100;
        final int lineH = font.getHeightCeil(tip, fontOptions);

        final ArrayList<String> lines = GameUtils.breakString(tip, fontOptions, w);
        lines.add(0, "Tip:");

        final int h = lineH * lines.size();
        final int y = Screen.getHudHeight() - 5 - h;

        background.getDrawOptions(x - 5, y - 5, w + 10, h + lineH).draw();
        for (int i = 0; i < lines.size(); i++) {
            final String line = lines.get(i);
            font.drawString(x, y + i * lineH, line, fontOptions);
        }
    }

}
