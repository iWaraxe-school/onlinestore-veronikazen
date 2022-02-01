import helpers.StoreHelper;
import helpers.operations.OperationManager;
import store.Store;

public class StoreApp {

    public static void main(String[] args) throws Exception {
        Store onlineStore = Store.getInstance();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        storeHelper.fillStoreRandomly();
        onlineStore.printAllCategories();
        OperationManager operationManager = new OperationManager();
        operationManager.selectOperation();
        }
    }

