package com.estetify.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class UserHairdresserDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank
        private String name;

        @Email
        @NotBlank
        private String email;

        @NotBlank
        @Size(min = 8)
        private String password;

        private String instagramProfile;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private String email;
        private String instagramProfile;
        private Double rating;
        private List<ProductSummary> products;
        private List<ServiceSummary> services;

        @Getter @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ProductSummary {
            private Long id;
            private String name;
            private Double price;
            private String image;
        }

        @Getter @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ServiceSummary {
            private Long id;
            private String name;
            private Double price;
            private LocalDateTime availabilityDate;
            private List<MaterialSummary> materialsUsed;

            @Getter @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class MaterialSummary {
                private Long id;
                private String name;
            }
        }
    }
}
