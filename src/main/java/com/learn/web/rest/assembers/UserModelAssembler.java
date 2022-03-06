package com.learn.web.rest.assembers;

import com.learn.web.rest.controller.UserRestApiController;
import com.learn.web.rest.entities.UserEntity;
import com.learn.web.rest.model.UserModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {

    public UserModelAssembler() {
        super(UserRestApiController.class, UserModel.class);
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends UserEntity> entities) {
        CollectionModel<UserModel> models = super.toCollectionModel(entities);

        models.add(linkTo(methodOn(UserRestApiController.class).getUsers()).withSelfRel());

        return models;
    }

    @Override
    public UserModel toModel(UserEntity entity) {
        UserModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(UserRestApiController.class).getUserById(entity.getId())).withSelfRel());

        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setPassword(entity.getPassword());
        model.setEmail(entity.getEmail());

        return model;
    }
}
