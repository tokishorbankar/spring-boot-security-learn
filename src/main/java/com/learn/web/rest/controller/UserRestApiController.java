package com.learn.web.rest.controller;


import com.learn.web.rest.assembers.UserModelAssembler;
import com.learn.web.rest.entities.UserEntity;
import com.learn.web.rest.exception.BadRequestException;
import com.learn.web.rest.exception.ResourceNotFoundException;
import com.learn.web.rest.model.UserModel;
import com.learn.web.rest.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestApiController {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private PagedResourcesAssembler<UserEntity> pagedResourcesAssembler;


    @GetMapping
    public ResponseEntity<CollectionModel<UserModel>> getUsers() throws BadRequestException {

        final List<UserEntity> actorEntities = userService.findAll();

        return new ResponseEntity<>(userModelAssembler.toCollectionModel(actorEntities), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") final Long id)
            throws BadRequestException, ResourceNotFoundException {
        return userService.findById(id).map(userModelAssembler::toModel).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @GetMapping("/all-list")
    public ResponseEntity<PagedModel<UserModel>> getUsersByPageable(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) final Pageable pageable)
            throws BadRequestException {

        final Page<UserEntity> entities = userService.findAll(pageable);

        final PagedModel<UserModel> collModel = pagedResourcesAssembler.toModel(entities, userModelAssembler);

        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(final @Valid @RequestBody UserModel model)
            throws BadRequestException {

        final UserEntity entity = modelMapper.map(model, UserEntity.class);

        final UserEntity results = userService.saveOrUpdate(entity);

        final URI url = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(results.getId())
                .toUri();

        return ResponseEntity.created(url).eTag(url.getPath()).body(modelMapper.map(results, UserModel.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateRecipe(@PathVariable(required = true, value = "id") final Long id,
                                                  final @Valid @RequestBody UserModel model) throws BadRequestException, ResourceNotFoundException {

        final UserEntity entity = userService.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        entity.setUsername(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setEmail(model.getEmail());

        final UserEntity results = userService.saveOrUpdate(entity);

        final URI url = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(results.getId())
                .toUri();

        return ResponseEntity.ok().location(url).eTag(url.getPath()).body(modelMapper.map(results, UserModel.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") final Long id)
            throws BadRequestException, ResourceNotFoundException {

        final UserEntity results = userService.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        userService.deleteById(results.getId());

        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}