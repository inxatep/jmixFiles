package com.sample.drivers.screen.driver;

import com.sample.drivers.entity.Document;
import io.jmix.core.FileRef;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.BrowserFrame;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.FileStorageResource;
import io.jmix.ui.component.LinkButton;
import io.jmix.ui.download.DownloadFormat;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.sample.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;


@UiController("Driver.edit")
@UiDescriptor("driver-edit.xml")
@EditedEntityContainer("driverDc")
public class DriverEdit extends StandardEditor<Driver> {

    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader;

    @Install(to = "documentsTable.fileRef", subject = "columnGenerator")
    private Component documentsTableFileRefColumnGenerator(Document document) {
        if(document.getFileRef() != null) {
            LinkButton linkButton = uiComponents.create(LinkButton.class);
            linkButton.setCaption(document.getFileRef().getFileName());
            linkButton.setAction(new BaseAction("download").withHandler(actionPerformedEvent -> {
                downloader.download(document.getFileRef());
            }));
            return linkButton;
        } else {
            return null;
        }
    }
}