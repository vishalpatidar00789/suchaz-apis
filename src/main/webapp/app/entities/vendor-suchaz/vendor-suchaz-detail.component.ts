import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { VendorSuchaz } from './vendor-suchaz.model';
import { VendorSuchazService } from './vendor-suchaz.service';

@Component({
    selector: 'jhi-vendor-suchaz-detail',
    templateUrl: './vendor-suchaz-detail.component.html'
})
export class VendorSuchazDetailComponent implements OnInit, OnDestroy {

    vendor: VendorSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private vendorService: VendorSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVendors();
    }

    load(id) {
        this.vendorService.find(id)
            .subscribe((vendorResponse: HttpResponse<VendorSuchaz>) => {
                this.vendor = vendorResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVendors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'vendorListModification',
            (response) => this.load(this.vendor.id)
        );
    }
}
