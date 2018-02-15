import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { StoreImageSuchaz } from './store-image-suchaz.model';
import { StoreImageSuchazPopupService } from './store-image-suchaz-popup.service';
import { StoreImageSuchazService } from './store-image-suchaz.service';
import { StoreSuchaz, StoreSuchazService } from '../store-suchaz';

@Component({
    selector: 'jhi-store-image-suchaz-dialog',
    templateUrl: './store-image-suchaz-dialog.component.html'
})
export class StoreImageSuchazDialogComponent implements OnInit {

    storeImage: StoreImageSuchaz;
    isSaving: boolean;

    stores: StoreSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private storeImageService: StoreImageSuchazService,
        private storeService: StoreSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.storeService.query()
            .subscribe((res: HttpResponse<StoreSuchaz[]>) => { this.stores = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.storeImage, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.storeImage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.storeImageService.update(this.storeImage));
        } else {
            this.subscribeToSaveResponse(
                this.storeImageService.create(this.storeImage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<StoreImageSuchaz>>) {
        result.subscribe((res: HttpResponse<StoreImageSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: StoreImageSuchaz) {
        this.eventManager.broadcast({ name: 'storeImageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackStoreById(index: number, item: StoreSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-store-image-suchaz-popup',
    template: ''
})
export class StoreImageSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private storeImagePopupService: StoreImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.storeImagePopupService
                    .open(StoreImageSuchazDialogComponent as Component, params['id']);
            } else {
                this.storeImagePopupService
                    .open(StoreImageSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
