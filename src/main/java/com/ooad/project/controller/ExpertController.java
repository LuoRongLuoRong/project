package com.ooad.project.controller;

import com.ooad.project.entity.Expert;
import com.ooad.project.repo.ExpertRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Expert: Admin
 * Date: 2020/12/26
 * Time: 17:11
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */

@RestController
public class ExpertController {

}
