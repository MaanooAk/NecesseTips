package maanooak.tips;

import java.awt.Rectangle;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.presets.FeedbackForm;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = FeedbackForm.class, name = "draw", arguments = { TickManager.class, PlayerMob.class,
        Rectangle.class })
public class PatchFeedbackFormDraw {

//    public void draw(TickManager tickManager, PlayerMob perspective, Rectangle renderBox) {

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    static boolean onEnter(@Advice.This FeedbackForm feedbackForm,
            @Advice.Argument(0) TickManager tickManager,
            @Advice.Argument(1) PlayerMob perspective,
            @Advice.Argument(2) Rectangle renderBox) {

        Tips.draw();

        return false;
    }

}
