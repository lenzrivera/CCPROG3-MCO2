package controllers.templates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.DenominationMap;
import model.Slot;
import model.VendingMachine;
import model.VendingMachineModel;
import util.Controller;
import views.templates.CreateMachineView;

public abstract class CreateMachineController extends Controller {
    protected VendingMachineModel model;

    public CreateMachineController(VendingMachineModel m) {
        model = m;
    }

    protected abstract VendingMachine getMachine();
    protected abstract CreateMachineView getView();

    protected void setConstants() {
        getView().getBasicInfoPanel()
                 .setMinSlotCount(VendingMachine.MIN_SLOT_COUNT);
        getView().getBasicInfoPanel()
                 .setMinSlotCapacity(Slot.MIN_MAX_CAPACITY);

        getView().getStockChangePanel().setDenominations(
            Arrays.stream(DenominationMap.VALID_DENOMINATIONS).boxed().toList());
    }

    protected void updateDenominationTable(DenominationMap denomMap) {
        Map<Double, Integer> rawMap = denomMap.getDenominations();

        ArrayList<Double> denominations = new ArrayList<>(rawMap.keySet());
        ArrayList<Integer> quantities = new ArrayList<>(rawMap.values());

        for (int i = 0; i < rawMap.size(); i++) {
            getView().getStockChangePanel()
                     .setDenominationCell(i + 1, denominations.get(i));
            getView().getStockChangePanel()
                     .setQuantityCell(i + 1, quantities.get(i));
        }
    }

    protected void updateSlotTable(List<Slot> slots) {
        for (int i = 0; i < slots.size(); i++) {
            String name = (slots.get(i).getSampleItem() == null) 
                ? "[empty]" 
                : slots.get(i).getSampleItem().getName();

            getView().getStockItemsPanel().setSlotNumberCell(i + 1, i + 1);
            getView().getStockItemsPanel().setItemNameCell(i + 1, name);
        }
    }
}
