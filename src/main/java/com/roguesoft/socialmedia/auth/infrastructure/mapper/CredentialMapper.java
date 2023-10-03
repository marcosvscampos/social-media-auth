package com.roguesoft.socialmedia.auth.infrastructure.mapper;

import com.roguesoft.socialmedia.auth.domain.entity.credential.Credential;
import com.roguesoft.socialmedia.auth.domain.entity.credential.CredentialType;
import com.roguesoft.socialmedia.auth.domain.entity.credential.PasswordCredential;
import com.roguesoft.socialmedia.auth.infrastructure.model.CredentialModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CredentialMapper extends DataMapper<Credential, CredentialModel> {

    public CredentialMapper(final ModelMapper mapper) {
        super(mapper);
        Converter<CredentialType, String> credentialTypeConverter = ctx -> Objects.nonNull(ctx.getSource()) ?
                ctx.getSource().name() : null;

        TypeMap<Credential, CredentialModel> typeMapToModel = super.getMapper().createTypeMap(Credential.class, CredentialModel.class);
        typeMapToModel.addMapping(Credential::getSecretValue, CredentialModel::setValue);

        typeMapToModel.addMappings(mappings -> mappings.using(credentialTypeConverter)
                .map(Credential::getCredentialType, CredentialModel::setType));

        TypeMap<CredentialModel, PasswordCredential> typeMapToEntityPassword = super.getMapper().createTypeMap(CredentialModel.class, PasswordCredential.class);
        typeMapToEntityPassword.addMapping(CredentialModel::getValue, PasswordCredential::setPassword);
    }

    @Override
    public CredentialModel toModel(final Credential entity){
        return super.getMapper().map(entity, CredentialModel.class);
    }

    @Override
    public Credential toEntity(final CredentialModel model){
        Credential credential = CredentialType.valueOf(model.getType()).getCredential();
        return super.getMapper().map(model, credential.getClass());
    }

}
