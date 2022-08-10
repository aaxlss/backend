package com.backend.Backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateVersions {

    @GetMapping(path="/")
    public ResponseEntity ping(){
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/versions")
    public Integer comparingVerionNumber(@RequestParam String v1,@RequestParam String v2 ) {

        /*
         * 2 new array will be created base in the versions that the API is receiving
         * the versions will split using the dot (.) to generate the new spaces in the arrays
         * e.g 3.2.1 <-- this will generate a new array with 3 positions [3,2,1]
         */
        String arrV1[] = v1.split("\\.");
        String arrV2[] = v2.split("\\.");

        //We'll store the length of each array
        int lenV1 = arrV1.length;
        int lenV2 = arrV2.length;

        int i = 0;
        int j = 0;


        /*
         * This loop will help us to iterate in each position of the array and compare#
         * which version could be higher.
         * If the loop doesn't find the higher verions will finish a proceed with another validation.
         */
        while (i < lenV1 && j < lenV2) {
            int num1 = Integer.parseInt(arrV1[i]);
            int num2 = Integer.parseInt(arrV2[i]);
            i++;
            j++;
            if (num1 < num2) {
                return -1;
            } else if (num2 < num1) {
                return 1;
            }
        }
        /*
         * This validation will execute if the length of V1 is higher that the length V2.
         * For each number from the arrayV1 will compare if it is greater that 0 if this happends
         * that means that V1 is the higher
         */
        if (lenV1 > lenV2) {
            while (i < lenV1) {
                if (Integer.parseInt(arrV1[i]) > 0) {
                    return 1;
                }
                i++;
            }

        }
        /*
         * This validation will execute if the length of V2 is higher that the length V1.
         * For each number from the arrayV2 will compare if it is greater that 0 if this happends
         * that means that V2 is the higher
         */
        if (lenV2 > lenV1) {
            while (j < lenV2) {
                if (Integer.parseInt(arrV2[j]) > 0) {
                    return -1;
                }
                j++;
            }

        }

        return 0;
    }

}
