package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.ExitWithMessageAction;
import eapli.cafeteria.consoleapp.presentation.MyUserMenu;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.delivery.PreviewAvailableMealsController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.presentation.console.*;

import java.util.Map;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int SALES_OPTION = 2;
    private static final int CASH_REGISTER_OPTION = 3;


    @Override
    public boolean show() {
        drawFormTitle();
        displayAvailableMeals();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu);
        } else {
            renderer = new VerticalMenuRenderer(menu);
        }
        return renderer.show();
    }

    @Override
    protected void drawFormTitle() {
        super.drawFormTitle();

    }

    @Override
    public String headline() {
        return "eCafeteria POS [@" + Application.session().session().authenticatedUser().id() + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuWithAvailableMeals(myUserMenu)));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        if (Application.session().session().authenticatedUser().isAuthorizedTo(ActionRight.SALE)) {
            // TODO
            final Menu salesMenu = new SalesMenu();
            mainMenu.add(new SubMenu(SALES_OPTION, salesMenu, new ShowVerticalSubMenuWithAvailableMeals(salesMenu)));
        }

        if (Application.session().session().authenticatedUser().isAuthorizedTo(ActionRight.SALE)) {
            // TODO
            final Menu cashRegisterMenu = new CashRegisterMenu();
            mainMenu.add(new SubMenu(CASH_REGISTER_OPTION, cashRegisterMenu, new ShowVerticalSubMenuWithAvailableMeals(cashRegisterMenu)));
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private void displayAvailableMeals() {
        PreviewAvailableMealsController previewAvailableMealsController = new PreviewAvailableMealsController();
        System.out.println();
        System.out.println("Available meals per dish type:");
        Map<DishType, Integer> availableDishTypes = previewAvailableMealsController.availableDishTypes();
        for (Map.Entry<DishType, Integer> entry : availableDishTypes.entrySet()) {
            System.out.printf("%10s -> %d available\n", entry.getKey().acronym(), entry.getValue());
        }
        System.out.println();
    }
}
