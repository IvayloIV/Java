package santasWorkshop.repositories;

import santasWorkshop.models.Dwarf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static santasWorkshop.common.ExceptionMessages.DWARF_DOESNT_EXIST;

public class DwarfRepository implements Repository<Dwarf> {

    private List<Dwarf> dwarfs;

    public DwarfRepository() {
        this.dwarfs = new ArrayList<>();
    }

    @Override
    public List<Dwarf> getModels() {
        return Collections.unmodifiableList(this.dwarfs);
    }

    @Override
    public void add(Dwarf model) {
        this.dwarfs.add(model);
    }

    @Override
    public boolean remove(Dwarf model) {
        return this.dwarfs.remove(model);
    }

    @Override
    public Dwarf findByName(String name) {
        Supplier<? extends RuntimeException> exceptionDwarfNotFound = () -> new IllegalArgumentException(DWARF_DOESNT_EXIST);

        return this.dwarfs.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst()
                .orElseThrow(exceptionDwarfNotFound);
    }
}
