import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ItemImageSuchaz } from './item-image-suchaz.model';
import { ItemImageSuchazService } from './item-image-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-item-image-suchaz',
    templateUrl: './item-image-suchaz.component.html'
})
export class ItemImageSuchazComponent implements OnInit, OnDestroy {
itemImages: ItemImageSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemImageService: ItemImageSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.itemImageService.query().subscribe(
            (res: HttpResponse<ItemImageSuchaz[]>) => {
                this.itemImages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInItemImages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ItemImageSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInItemImages() {
        this.eventSubscriber = this.eventManager.subscribe('itemImageListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
