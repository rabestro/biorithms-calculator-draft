package reports;

import biorhytms.Biorhythm;
import biorhytms.Condition;
import lombok.val;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnnualReport extends AbstractReport {
    private static final String RESOURCE = "report/annual-report";

    public AnnualReport(final ReportData reportData) {
        super(RESOURCE, reportData);
    }

    @Override
    public void run() {
        val leftPart = format("annual.header.left.format", birthday());
        val rightPart = format("annual.header.right.format", date());

        printf("annual.header.format", leftPart, rightPart);

        Function<Month, String> shortName = month ->
                String.format("%-6s", month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));

        final var headerMonths = "      " + Arrays
                .stream(Month.values())
                .map(shortName)
                .collect(Collectors.joining());

        final var headerPEI = "   " + "   PEI".repeat(Month.values().length);
        System.out.println(headerMonths);
        System.out.println(headerPEI);

        for (int day = 1; day < 32; day++) {
            System.out.printf("%3d   ", day);
            for (int month = 1; month < 13; month++) {
                System.out.printf("%-6s", getIndicators(month, day));
            }
            System.out.println();
        }
    }

    private String getIndicators(final int month, final int day) {
        try {
            final var date = LocalDate.of(reportData.getYear(), month, day);
            return Biorhythm.primary()
                    .map(biorhythm -> biorhythm.new Value(birthday(), date))
                    .map(Biorhythm.Value::getValue)
                    .map(Condition::of)
                    .map(Condition::getSymbol)
                    .collect(Collectors.joining());

        } catch (DateTimeException e) {
            return "";
        }
    }

}
