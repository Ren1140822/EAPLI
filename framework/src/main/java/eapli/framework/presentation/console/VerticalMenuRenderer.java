/**
 *
 */
package eapli.framework.presentation.console;

/**
 * @author pgsou_000
 *
 */
public class VerticalMenuRenderer extends MenuRenderer {
	public VerticalMenuRenderer(Menu menu) {
		super(menu);
	}

	@Override
	protected void doShow() {
		for (final MenuItem item : menu.itens()) {
			item.show();
			System.out.println();
		}
	}
}
