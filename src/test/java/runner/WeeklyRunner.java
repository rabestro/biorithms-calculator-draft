package runner;

import lv.id.jc.biorhytm.report.WeeklyReport;

public class WeeklyRunner extends AbstractRunner {
    static {
        reportRunner = WeeklyReport::new;
    }
}
