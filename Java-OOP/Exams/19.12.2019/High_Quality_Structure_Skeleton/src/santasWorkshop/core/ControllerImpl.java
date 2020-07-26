package santasWorkshop.core;

import santasWorkshop.models.*;
import santasWorkshop.repositories.DwarfRepository;
import santasWorkshop.repositories.PresentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static santasWorkshop.common.ConstantMessages.*;
import static santasWorkshop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private DwarfRepository dwarfRepository;

    private PresentRepository presentRepository;

    private Workshop workshop;

    private int totalBrokenInstruments;

    public ControllerImpl() {
        this.dwarfRepository = new DwarfRepository();
        this.presentRepository = new PresentRepository();
        this.workshop = new WorkshopImpl();
    }

    @Override
    public String addDwarf(String type, String dwarfName) {
        Dwarf newDwarf;
        switch (type) {
            case "Happy":
                newDwarf = new Happy(dwarfName);
                break;
            case "Sleepy":
                newDwarf = new Sleepy(dwarfName);
                break;
            default:
                throw new IllegalArgumentException(DWARF_TYPE_DOESNT_EXIST);
        }

        this.dwarfRepository.add(newDwarf);
        return String.format(ADDED_DWARF, type, dwarfName);
    }

    @Override
    public String addInstrumentToDwarf(String dwarfName, int power) {
        Dwarf dwarf = this.dwarfRepository.findByName(dwarfName);
        dwarf.addInstrument(new InstrumentImpl(power));
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_DWARF, power, dwarf.getName());
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        this.presentRepository.add(new PresentImpl(presentName, energyRequired));
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Present present = this.presentRepository.findByName(presentName);

        List<Dwarf> dwarfs = this.dwarfRepository.getModels().stream()
                .filter(d -> d.getEnergy() > 50)
                .collect(Collectors.toList());

        if (dwarfs.size() == 0) {
            throw new IllegalArgumentException(NO_DWARF_READY);
        }

        String isDone = "not done";
        for (Dwarf dwarf : dwarfs) {
            long notBrokenInst = dwarf.getInstruments().stream().filter(i -> !i.isBroken()).count();
            this.workshop.craft(present, dwarf);
            long brokenInst = dwarf.getInstruments().stream().filter(i -> !i.isBroken()).count();
            totalBrokenInstruments += notBrokenInst - brokenInst;
            if(present.isDone()) {
                isDone = "done";
                break;
            }
        }

        return String.format(PRESENT_DONE, presentName, isDone) + String.format(COUNT_BROKEN_INSTRUMENTS, totalBrokenInstruments);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        long craftedPresents = this.presentRepository.getModels()
                .stream()
                .filter(Present::isDone)
                .count();

        sb.append(String.format("%s presents are done!", craftedPresents));
        sb.append(System.lineSeparator());
        sb.append("Dwarfs info:");
        sb.append(System.lineSeparator());
        this.dwarfRepository.getModels().forEach(sb::append);

        return sb.toString().trim();
    }
}
