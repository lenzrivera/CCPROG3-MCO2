package controllers.templates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileNameExtensionFilter;

import model.DenominationMap;
import model.Slot;
import model.VendingMachine;
import model.VendingMachineModel;
import util.Controller;
import views.templates.CreateMachineView;

public abstract class CreateMachineController<
    T extends CreateMachineView<?>, 
    U extends VendingMachine<?>> extends Controller
{
    protected VendingMachineModel model;
    protected T view;
    protected U machine;

    public CreateMachineController(VendingMachineModel m, T v) {
        model = m;
        view = v;
        machine = null;

        setConstants();
    }

    protected void setConstants() {
        view.getBasicInfoPanel()
            .setMinSlotCount(VendingMachine.MIN_SLOT_COUNT);
        view.getBasicInfoPanel()
            .setMinSlotCapacity(Slot.MIN_MAX_CAPACITY);

        // TODO: actually disallow non-image files as this can be circumvented
        FileNameExtensionFilter filter = 
            new FileNameExtensionFilter("Image Files", "jpg", "png");
        view.getStockItemsPanel().setFileFilter(filter);

        view.getStockChangePanel().setDenominations(
            Arrays.stream(DenominationMap.VALID_DENOMINATIONS).boxed().toList());
    }

    protected void updateDenominationTable(DenominationMap denomMap) {
        Map<Double, Integer> rawMap = denomMap.getDenominations();

        ArrayList<Double> denominations = new ArrayList<>(rawMap.keySet());
        ArrayList<Integer> quantities = new ArrayList<>(rawMap.values());

        for (int i = 0; i < rawMap.size(); i++) {
            view.getStockChangePanel()
                .setDenominationCell(i + 1, denominations.get(i));
            view.getStockChangePanel()
                .setQuantityCell(i + 1, quantities.get(i));
        }
    }

    protected void updateSlotTable(List<? extends Slot> slots) {
        for (int i = 0; i < slots.size(); i++) {
            String name = (slots.get(i).getSampleItem() == null) 
                ? "[empty]" 
                : slots.get(i).getSampleItem().getName();

            view.getStockItemsPanel().setSlotNumberCell(i + 1, i + 1);
            view.getStockItemsPanel().setItemNameCell(i + 1, name);
        }
    }
}
