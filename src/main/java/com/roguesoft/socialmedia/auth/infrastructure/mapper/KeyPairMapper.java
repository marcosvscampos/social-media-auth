package com.roguesoft.socialmedia.auth.infrastructure.mapper;

import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import com.roguesoft.socialmedia.auth.infrastructure.mapper.formatter.KeyParser;
import com.roguesoft.socialmedia.auth.infrastructure.model.KeyPairModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Objects;

@Component
@Primary
public class KeyPairMapper extends DataMapper<KeyPair, KeyPairModel> {

    private final KeyParser publicKeyParser;

    private final KeyParser privateKeyParser;

    public KeyPairMapper(final ModelMapper mapper,
                         final KeyParser publicKeyParser,
                         final KeyParser privateKeyParser) {
        super(mapper);
        this.publicKeyParser = publicKeyParser;
        this.privateKeyParser = privateKeyParser;

        Converter<PublicKey, String> convertPublicKeyToBase64Str = ctx -> Objects.nonNull(ctx.getSource()) ?
                Base64.getEncoder().encodeToString(ctx.getSource().getEncoded()) : null;
        Converter<PrivateKey, String> convertPrivateKeyToBase64Str = ctx -> Objects.nonNull(ctx.getSource()) ?
                Base64.getEncoder().encodeToString(ctx.getSource().getEncoded()) : null;

        TypeMap<KeyPair, KeyPairModel> typeMapToModel = super.getMapper().createTypeMap(KeyPair.class, KeyPairModel.class);
        typeMapToModel.addMappings(mappings -> mappings.using(convertPublicKeyToBase64Str).map(KeyPair::getPublicKey, KeyPairModel::setPublicKey));
        typeMapToModel.addMappings(mappings -> mappings.using(convertPrivateKeyToBase64Str).map(KeyPair::getPrivateKey, KeyPairModel::setPrivateKey));

        Converter<String, PublicKey> convertStrToPublicKey = ctx -> {
            String value = ctx.getSource();
            if(Objects.nonNull(value)) {
               return (PublicKey) this.publicKeyParser.parse(value);
            }
            return null;
        };

        Converter<String, PrivateKey> convertStrToPrivateKey = ctx -> {
            String value = ctx.getSource();
            if(Objects.nonNull(value)) {
                return (PrivateKey) this.privateKeyParser.parse(value);
            }
            return null;
        };

        TypeMap<KeyPairModel, KeyPair> typeMapToEntity = super.getMapper().createTypeMap(KeyPairModel.class, KeyPair.class);
        typeMapToEntity.addMappings(mappings -> mappings.using(convertStrToPublicKey).map(KeyPairModel::getPublicKey, KeyPair::setPublicKey));
        typeMapToEntity.addMappings(mappings -> mappings.using(convertStrToPrivateKey).map(KeyPairModel::getPrivateKey, KeyPair::setPrivateKey));

    }

    @Override
    public KeyPairModel toModel(final KeyPair entity){
        return super.getMapper().map(entity, KeyPairModel.class);
    }

    @Override
    public KeyPair toEntity(final KeyPairModel model){
        return super.getMapper().map(model, KeyPair.class);
    }

}
