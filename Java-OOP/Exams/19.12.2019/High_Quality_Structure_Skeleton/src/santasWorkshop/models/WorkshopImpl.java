package santasWorkshop.models;

public class WorkshopImpl implements Workshop {

    @Override
    public void craft(Present present, Dwarf dwarf) {
        Instrument currInstrument = null;

        while (!present.isDone() && dwarf.canWork()) {
            if (currInstrument == null || currInstrument.isBroken()) {
                currInstrument = this.getInstrument(dwarf);
            }

            if (currInstrument == null) {
                break;
            }

            present.getCrafted();
            dwarf.work();
            currInstrument.use();
        }
    }

    private Instrument getInstrument(Dwarf dwarf) {
        return dwarf.getInstruments()
                .stream()
                .filter(i -> !i.isBroken())
                .findFirst()
                .orElse(null);
    }
}
