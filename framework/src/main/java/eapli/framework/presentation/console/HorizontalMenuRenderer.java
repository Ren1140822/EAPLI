/**
 *
 */
package eapli.framework.presentation.console;

/**
 * @author pgsou_000
 *
 */
public class HorizontalMenuRenderer extends MenuRenderer {

	/**
	 *
	 */
	public HorizontalMenuRenderer(Menu menu) {
		super(menu);
	}

	@Override
	protected void doShow() {
		for (final MenuItem item : menu.itens()) {
			System.out.print("| ");
			item.show();
			System.out.print(" | ");
		}
	}
}
