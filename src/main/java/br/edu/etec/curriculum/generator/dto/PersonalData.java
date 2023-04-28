package br.edu.etec.curriculum.generator.dto;

import br.edu.etec.curriculum.generator.util.Formatters;
import jakarta.validation.constraints.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public record PersonalData(
        @NotNull String name,
        List<String> phones,
        @NotNull String maritalStatus,
        @NotNull String email,
        @NotNull String bornIn,
        @NotNull Integer age,
        Integer childrenCount,
        @NotNull Address address,
        String driverLicenseType
) {
    public String getBasicInfo(){
        var builder = new StringBuilder();

        builder.append(bornIn).append(", ").append(maritalStatus).append(", ").append(age).append(" anos");

        var childrenCount = defaultIfNull(childrenCount(), 0);

        if (childrenCount > 0){
            builder.append(", ").append(childrenCount).append(" filhos");
        }

        return builder.toString();
    }

    public String getAddressFormatted(){
        return address.formatted();
    }

    public String getPhoneFormatted(){
        var phonesConcatenated = phones.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .map(Formatters::formatPhone)
                .collect(Collectors.joining(" / "));

        return String.format("Contato telefônico: %s", phonesConcatenated);
    }
}
