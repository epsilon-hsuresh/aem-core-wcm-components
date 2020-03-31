/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2017 Adobe
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.cq.wcm.core.components.internal.models.v1;

import javax.annotation.Nonnull;

import com.adobe.cq.wcm.core.components.models.TableOfContent;
import com.adobe.cq.wcm.core.components.models.TableOfContentItem;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
    adapters = {TableOfContent.class, ComponentExporter.class},
    resourceType = {TableOfContentImpl.RESOURCE_TYPE}
    )
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
    extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class TableOfContentImpl implements  TableOfContent {


    public static final String RESOURCE_TYPE = "core/wcm/components/tableOfContent/v1/tableOfContent";
    private static final String PROP_DEFAULT_TITLE = "Table of Content";

    @Self
    private SlingHttpServletRequest slingRequest;

    @ScriptVariable
    private PageManager pageManager;

    @ValueMapValue
    @Default(values = PROP_DEFAULT_TITLE)
    private String title;

    @ValueMapValue
    @Default(values = PROP_DEFAULT_TITLE)
    private List<TableOfContentItem> content;


    @Override
    public String getTitle() { return title; }

    @Override
    public List<TableOfContentItem> getItems() { return content; }

    @Nonnull
    @Override
    public String getExportedType() {
        return slingRequest.getResource().getResourceType();
    }

}