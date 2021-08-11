package lv.id.jc.biorhythm.stage;

import lv.id.jc.biorhythm.Context;
import lv.id.jc.biorhythm.report.AgeInfo;
import lv.id.jc.biorhythm.report.ZodiacInfo;
import lv.id.jc.biorhythm.service.AskBirthday;

public class Stage1 {
    public static void main(String[] args) {
        var context = new Context();

        new AskBirthday(context).run();
        new AgeInfo(context).run();
        new ZodiacInfo(context).run();
    }
}