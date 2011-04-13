package org.vaadin.kanban.web;

import java.util.List;

import org.vaadin.kanban.domain.Card;
import org.vaadin.kanban.domain.StateColumn;
import org.vaadin.navigator.Navigator;

import com.vaadin.Application;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class KanbanBoard extends CustomComponent implements Navigator.View {

    private GridLayout grid;

    /**
     * The constructor should first build the main layout, set the composition
     * root and then do any custom initialization.
     * 
     * The constructor will not be automatically regenerated by the visual
     * editor.
     */
    public KanbanBoard() {
        setWidth("100.0%");
        setHeight("100.0%");
        AbsoluteLayout mainLayout = new AbsoluteLayout();

        mainLayout.setMargin(true);

        grid = new GridLayout();
        grid.setStyleName("board");
        grid.setSizeFull();
        grid.setImmediate(true);

        grid.setMargin(true);
        grid.setSpacing(true);

        mainLayout.addComponent(grid, "top:0.0px;left:0.0px;");
        addStyleName("no-horizontal-drag-hints");
        setCompositionRoot(mainLayout);
    }

    @Override
    public String getWarningForNavigatingFrom() {
        return null;
    }

    @Override
    public void init(Navigator navigator, Application application) {
        // nothing to do
    }

    @Override
    public void navigateTo(String requestedDataId) {
        update();
    }

    void update() {
        List<StateColumn> stateColumns = StateColumn.findAllStateColumns();

        grid.removeAllComponents();
        if (stateColumns.size() < 1) {
            return;
        }
        grid.setColumns(stateColumns.size());
        grid.setRows(3);
        grid.setRowExpandRatio(0, 0);
        grid.setRowExpandRatio(1, 2);
        grid.setRowExpandRatio(2, 0);
        for (StateColumn stateColumn : stateColumns) {
            Label name = new Label("<h2>" + stateColumn.getName() + "</h2>",
                    Label.CONTENT_XHTML);
            name.setStyleName("header");
            name.setSizeUndefined();
            name.setWidth(100, UNITS_PERCENTAGE);

            int wipLimit = stateColumn.getWorkInProgressLimit();
            Label wip = new Label("" + (wipLimit > 0 ? wipLimit : ""),
                    Label.CONTENT_XHTML);
            wip.setStyleName("wip");
            wip.setSizeUndefined();
            wip.setWidth(100, UNITS_PERCENTAGE);

            VerticalLayout header = new VerticalLayout();
            header.setSizeUndefined();
            header.setWidth(100, UNITS_PERCENTAGE);
            header.addComponent(name);
            header.addComponent(wip);

            KanbanColumn column = new KanbanColumn(this, stateColumn);
            for (Card card : Card.findCardsByStateColumn(stateColumn)
                    .getResultList()) {
                column.addComponent(new KanbanCard(card));
            }

            Label dod = new Label("<b>Definition of done</b>"
                    + stateColumn.getDefinitionOfDone(), Label.CONTENT_XHTML);
            dod.setStyleName("dod");
            dod.setSizeUndefined();
            dod.setWidth(100, UNITS_PERCENTAGE);

            int sortOrder = stateColumn.getSortOrder();
            int row = 0;
            grid.addComponent(header, sortOrder, row++);
            grid.addComponent(column, sortOrder, row++);
            grid.addComponent(dod, sortOrder, row++);
            grid.setColumnExpandRatio(sortOrder, 1);
        }
    }
}
