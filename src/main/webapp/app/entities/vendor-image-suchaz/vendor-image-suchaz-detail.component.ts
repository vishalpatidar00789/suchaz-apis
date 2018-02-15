import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { VendorImageSuchaz } from './vendor-image-suchaz.model';
import { VendorImageSuchazService } from './vendor-image-suchaz.service';

@Component({
    selector: 'jhi-vendor-image-suchaz-detail',
    templateUrl: './vendor-image-suchaz-detail.component.html'
})
export class VendorImageSuchazDetailComponent implements OnInit, OnDestroy {

    vendorImage: VendorImageSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private vendorImageService: VendorImageSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVendorImages();
    }

    load(id) {
        this.vendorImageService.find(id)
            .subscribe((vendorImageResponse: HttpResponse<VendorImageSuchaz>) => {
                this.vendorImage = vendorImageResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVendorImages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'vendorImageListModification',
            (response) => this.load(this.vendorImage.id)
        );
    }
}
