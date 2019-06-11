package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDateTime;

import static kata.ex01.model.RouteType.RURAL;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {
        if (drive.getRouteType() == RURAL
                && (isWeekdayMorning(drive.getEnteredAt())
                || isWeekdayMorning(drive.getExitedAt())
                || isWeekdayEvening(drive.getEnteredAt())
                || isWeekdayEvening(drive.getExitedAt()))) {
            if (drive.getDriver().getCountPerMonth() >= 10) {
                return 50;
            } else if (drive.getDriver().getCountPerMonth() >= 5) {
                return 30;
            }
        }

        if ((drive.getVehicleFamily() == VehicleFamily.STANDARD
                || drive.getVehicleFamily() == VehicleFamily.MINI
                || drive.getVehicleFamily() == VehicleFamily.MOTORCYCLE)
                && (HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate())
                || HolidayUtils.isHoliday(drive.getExitedAt().toLocalDate()))
                && drive.getRouteType() == RURAL
        ) {
            return 30;
        }



        return 0;
    }

    private boolean isWeekdayMorning(LocalDateTime time) {
        return !HolidayUtils.isHoliday(time.toLocalDate()) &&
                6 <= time.getHour() && time.getHour() < 9;
    }

    private boolean isWeekdayEvening(LocalDateTime time) {
        return !HolidayUtils.isHoliday(time.toLocalDate()) &&
                17 <= time.getHour() && time.getHour() < 20;
    }
}
