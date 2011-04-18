// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.vaadin.kanban.web.crud;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import java.lang.Class;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.vaadin.kanban.domain.StateColumn;
import org.vaadin.kanban.web.EntityTableColumnGenerator;

privileged aspect StateColumnView_Roo_VaadinEntityView {
    
    public String StateColumnView.getEntityName() {
        return "State Column";
    }
    
    public Class<StateColumn> StateColumnView.getEntityClass() {
        return StateColumn.class;
    }
    
    public boolean StateColumnView.isCreateAllowed() {
        return true;
    }
    
    public boolean StateColumnView.isUpdateAllowed() {
        return true;
    }
    
    public boolean StateColumnView.isDeleteAllowed() {
        return true;
    }
    
    public List<StateColumn> StateColumnView.listEntities() {
        List<StateColumn> list = StateColumn.findAllStateColumns();
        return list;
    }
    
    public StateColumn StateColumnView.saveEntity(StateColumn entity) {
        if (entity == null) {
            return null;
        }
        return (StateColumn) entity.merge();
    }
    
    public void StateColumnView.deleteEntity(StateColumn entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean StateColumnView.isNewEntity(StateColumn entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String StateColumnView.getIdProperty() {
        return "id";
    }
    
    public String StateColumnView.getVersionProperty() {
        return "version";
    }
    
    public StateColumn StateColumnView.createEntityInstance() {
        return new StateColumn();
    }
    
    public BeanContainer<Long, StateColumn> StateColumnView.getTableContainer() {
        BeanContainer<Long, StateColumn> container = new BeanContainer<Long, StateColumn>(StateColumn.class);
        container.setBeanIdProperty("id");
        for (StateColumn entity : StateColumn.findAllStateColumns()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item StateColumnView.getItemForEntity(StateColumn entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<StateColumn>(entity);
        }
        return item;
    }
    
    public StateColumn StateColumnView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<StateColumn>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object StateColumnView.getIdForEntity(StateColumn entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void StateColumnView.setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
    
}