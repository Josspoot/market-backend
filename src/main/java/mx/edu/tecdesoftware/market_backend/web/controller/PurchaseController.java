package mx.edu.tecdesoftware.market_backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.market_backend.domain.Purchase;
import mx.edu.tecdesoftware.market_backend.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchases", description = "Manage the purchases in the store")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("")
    @Operation(summary = "Get all purchases", description = "Return a list of all purchases")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of purchases")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get one specific Client", description = "Return the client you want")
    @ApiResponse(responseCode = "200", description = "Client Found")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public ResponseEntity<List<Purchase>> getByClient(
            @PathVariable @Parameter(description = "Client of the purchase to be retrieved", example = "4546221", required = true) String clientId) {
        return purchaseService.getByClient(clientId)
                .filter(purchases -> !purchases.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Save a new purchase", description = "Register a new purchase and return it", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Example Purchase",
                            value =
                                    """
                                            {
                                                        "clientId": "4546221",
                                                        "date": "2026-07-16T14:30:00",
                                                        "paymentMethod": "E",
                                                        "comment": "compra de prueba",
                                                        "status": "P",
                                                        "purchaseItems": [
                                                          {
                                                            "productId": 1,
                                                            "quantity": 5,
                                                            "total": 1500,
                                                            "active": true
                                                          },
                                                          {
                                                            "productId": 23,
                                                            "quantity": 2,
                                                            "total": 5000,
                                                            "active": true
                                                          }
                                                        ]
                                                      }
                                    """
                    )

            )
    ))
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid prurchase data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Purchase conflict")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
