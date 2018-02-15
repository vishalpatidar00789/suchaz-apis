import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ItemImageSuchaz } from './item-image-suchaz.model';
import { ItemImageSuchazPopupService } from './item-image-suchaz-popup.service';
import { ItemImageSuchazService } from './item-image-suchaz.service';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';

@Component({
    selector: 'jhi-item-image-suchaz-dialog',
    templateUrl: './item-image-suchaz-dialog.component.html'
})
export class ItemImageSuchazDialogComponent implements OnInit {

    itemImage: ItemImageSuchaz;
    isSaving: boolean;

    items: ItemSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private itemImageService: ItemImageSuchazService,
        private itemService: ItemSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.itemImage, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.itemImage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.itemImageService.update(this.itemImage));
        } else {
            this.subscribeToSaveResponse(
                this.itemImageService.create(this.itemImage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ItemImageSuchaz>>) {
        result.subscribe((res: HttpResponse<ItemImageSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ItemImageSuchaz) {
        this.eventManager.broadcast({ name: 'itemImageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackItemById(index: number, item: ItemSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-item-image-suchaz-popup',
    template: ''
})
export class ItemImageSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemImagePopupService: ItemImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.itemImagePopupService
                    .open(ItemImageSuchazDialogComponent as Component, params['id']);
            } else {
                this.itemImagePopupService
                    .open(ItemImageSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
