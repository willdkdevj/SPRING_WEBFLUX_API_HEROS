package br.com.supernova.apimembers.controller;

import br.com.supernova.apimembers.exception.MemberAlreadyRegisteredException;
import br.com.supernova.apimembers.exception.MemberNotFoundException;
import br.com.supernova.apimembers.model.Member;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemberControllerInterface {

    @ApiOperation(value = "Operation to list all registered members")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member catalog successfully returned")
    })
    public Flux<Member> getAllItems();

    @ApiOperation(value = "Operation to locate member(hero/villain)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Beer found successfully"),
            @ApiResponse(code = 404, message = "Could not find member reported")
    })
    public Mono<Member> findByIdMember(@PathVariable Long id) throws MemberNotFoundException;

    @ApiOperation(value = "Operation to locate member(hero/villain)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member found successfully"),
            @ApiResponse(code = 404, message = "Could not find member reported")
    })
    public  Mono<Member> findByNameMember(@PathVariable String name) throws MemberNotFoundException;

    @ApiOperation(value = "Operation for member(hero/villain) creation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Member successfully created"),
            @ApiResponse(code = 400, message = "It was not possible to register the informed member")
    })
    public Mono<Member> createMember(@RequestBody Member member) throws MemberAlreadyRegisteredException;

    @ApiOperation(value = "Operation to exclude member(Hero/Villain)")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Member registration successfully deleted"),
            @ApiResponse(code = 404, message = "Could not find the member registration for deletion")
    })
    public Mono<HttpStatus> deleteByIDMember(@PathVariable Long id) throws MemberNotFoundException;
}
