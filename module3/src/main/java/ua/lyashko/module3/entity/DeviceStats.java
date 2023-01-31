package ua.lyashko.module3.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeviceStats {
    Devices devices;
    long abonents;

    @Override
    public String toString () {
        return "\n DeviceStats{" +
                "devices=" + devices +
                ", abonents=" + abonents +
                '}';
    }
}
