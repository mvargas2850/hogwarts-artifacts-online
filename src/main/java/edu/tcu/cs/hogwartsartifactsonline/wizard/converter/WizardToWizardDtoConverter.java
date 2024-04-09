package edu.tcu.cs.hogwartsartifactsonline.wizard.converter;

import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import edu.tcu.cs.hogwartsartifactsonline.wizard.dto.WizardDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
//This is a Spring stereotype annotation that marks the class as a Spring-managed component,
//allowing it to be automatically detected and instantiated by Spring's component scanning mechanism.
//<Wizard (target type),WizardDto (source type)>
public class WizardToWizardDtoConverter implements Converter<Wizard, WizardDto> {
//useful when you need to convert entity objects to DTOs for transferring data between layers of an application,
//such as between the persistence layer and the presentation layer.
    @Override
    public WizardDto convert(Wizard source) {
        //conversion logic
        WizardDto wizardDto = new WizardDto(source.getId(),
                                            source.getName(),
                                            source.getNumberOfArtifacts());
        return wizardDto;
    }

}
