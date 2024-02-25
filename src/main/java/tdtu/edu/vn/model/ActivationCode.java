package tdtu.edu.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ActivationCode")

public class ActivationCode {
    @Id
    private String id;
    private List<String> bookId; // Can be one or multiple for combos
    private Order orderId;
    private String code;
    private Date startDate;
    private Date endDate;
    private ActivationCodeStatus status;
    private boolean isCombo; // True if it's a combo


    public ActivationCode(List<String> bookId, Order orderId, String code, Date startDate, Date endDate, ActivationCodeStatus status, boolean isCombo) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.isCombo = isCombo;
    }

    public enum ActivationCodeStatus {
        UNUSED,
        USED,
        EXPIRED
    }

//    @Component
//    public class ActivationCodeBeforeSaveCallback implements BeforeConvertCallback<ActivationCode> {
//
//        private SequenceGeneratorService sequenceGenerator;
//
//        @Autowired
//        public ActivationCodeBeforeSaveCallback(SequenceGeneratorService sequenceGenerator) {
//            this.sequenceGenerator = sequenceGenerator;
//        }
//
//        @Override
//        public ActivationCode onBeforeConvert(ActivationCode entity, String collection) {
//            if (entity.getId() == null || entity.getId().isEmpty()) { // Only set a new ID if it's null or empty
//                entity.setId(String.valueOf(sequenceGenerator.generateSequence(Publisher.SEQUENCE_NAME)));
//            }
//            return entity;
//        }
//    }
}
