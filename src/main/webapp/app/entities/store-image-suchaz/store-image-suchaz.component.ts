import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { StoreImageSuchaz } from './store-image-suchaz.model';
import { StoreImageSuchazService } from './store-image-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-store-image-suchaz',
    templateUrl: './store-image-suchaz.component.html'
})
export class StoreImageSuchazComponent implements OnInit, OnDestroy {
storeImages: StoreImageSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private storeImageService: StoreImageSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.storeImageService.query().subscribe(
            (res: HttpResponse<StoreImageSuchaz[]>) => {
                this.storeImages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInStoreImages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: StoreImageSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInStoreImages() {
        this.eventSubscriber = this.eventManager.subscribe('storeImageListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
