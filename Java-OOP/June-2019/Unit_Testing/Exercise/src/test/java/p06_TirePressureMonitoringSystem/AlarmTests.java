package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class AlarmTests {
    private Alarm alarm;
    private Sensor sensor;

    @Before
    public void initValues() throws NoSuchFieldException, IllegalAccessException {
        alarm = new Alarm();

        Field sensorField = Alarm.class.getDeclaredField("sensor");
        sensorField.setAccessible(true);

        sensor = Mockito.mock(Sensor.class);
        sensorField.set(alarm, sensor);
    }

    @Test
    public void checkAlarmLowPressure() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(16.0);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void checkAlarmHighPressure() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(22.0);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void checkAlarmForLowBound() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(17.0);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }

    @Test
    public void checkAlarmForHighBound() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(21.0);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }
}
