/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tests.server.component.grid.declarative;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.tests.design.DeclarativeTestBase;
import com.vaadin.ui.LegacyGrid;
import com.vaadin.ui.LegacyGrid.MultiSelectionModel;
import com.vaadin.ui.LegacyGrid.NoSelectionModel;
import com.vaadin.ui.LegacyGrid.SingleSelectionModel;

/**
 * Tests declarative support for Grid properties.
 *
 * @since
 * @author Vaadin Ltd
 */
public class GridDeclarativeAttributeTest
        extends DeclarativeTestBase<LegacyGrid> {

    @Test
    public void testBasicAttributes() {

        String design = "<vaadin-legacy-grid editable rows=20 frozen-columns=-1 "
                + "editor-save-caption='Tallenna' editor-cancel-caption='Peruuta' column-reordering-allowed>";

        LegacyGrid grid = new LegacyGrid();
        grid.setEditorEnabled(true);
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(20);
        grid.setFrozenColumnCount(-1);
        grid.setEditorSaveCaption("Tallenna");
        grid.setEditorCancelCaption("Peruuta");
        grid.setColumnReorderingAllowed(true);

        testRead(design, grid);
        testWrite(design, grid);
    }

    @Test
    public void testFrozenColumnsAttributes() {
        String design = "<vaadin-legacy-grid frozen-columns='2'><table>" //
                + "<colgroup><col><col><col></colgroup></table></vaadin-legacy-grid>";

        LegacyGrid grid = new LegacyGrid();
        grid.addColumn("property-0", String.class);
        grid.addColumn("property-1", String.class);
        grid.addColumn("property-2", String.class);
        grid.setFrozenColumnCount(2);

        testRead(design, grid);
    }

    @Test
    public void testSelectionMode() {
        String design = "<vaadin-legacy-grid selection-mode='none'>";
        assertSame(NoSelectionModel.class,
                read(design).getSelectionModel().getClass());

        design = "<vaadin-legacy-grid selection-mode='single'>";
        assertSame(SingleSelectionModel.class,
                read(design).getSelectionModel().getClass());

        design = "<vaadin-legacy-grid selection-mode='multi'>";
        assertSame(MultiSelectionModel.class,
                read(design).getSelectionModel().getClass());
    }
}