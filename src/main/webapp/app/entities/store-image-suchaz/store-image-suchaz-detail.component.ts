import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { StoreImageSuchaz } from './store-image-suchaz.model';
import { StoreImageSuchazService } from './store-image-suchaz.service';

@Component({
    selector: 'jhi-store-image-suchaz-detail',
    templateUrl: './store-image-suchaz-detail.component.html'
})
export class StoreImageSuchazDetailComponent implements OnInit, OnDestroy {

    storeImage: StoreImageSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private storeImageService: StoreImageSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStoreImages();
    }

    load(id) {
        this.storeImageService.find(id)
            .subscribe((storeImageResponse: HttpResponse<StoreImageSuchaz>) => {
                this.storeImage = storeImageResponse.body;
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

    registerChangeInStoreImages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'storeImageListModification',
            (response) => this.load(this.storeImage.id)
        );
    }
}
