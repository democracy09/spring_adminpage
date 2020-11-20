package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    // HTML <Form>
    // ajax 검색
    // 검색파라미터 ㅈㄴ많음
    // http post body -> data로 받겠따
    // json, xml, multipart-from / text-plain

    //@RequestMapping(method = RequestMethod.POST, path = "/postMethod") 같은말
    @PostMapping(value = "/postMethod")
    //@PostMapping(value = "/postMethod", produces = {"application-json"}) : 기본이 제이슨
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

}
