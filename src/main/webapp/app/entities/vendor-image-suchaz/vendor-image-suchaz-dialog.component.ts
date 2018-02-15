import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { VendorImageSuchaz } from './vendor-image-suchaz.model';
import { VendorImageSuchazPopupService } from './vendor-image-suchaz-popup.service';
import { VendorImageSuchazService } from './vendor-image-suchaz.service';
import { VendorSuchaz, VendorSuchazService } from '../vendor-suchaz';

@Component({
    selector: 'jhi-vendor-image-suchaz-dialog',
    templateUrl: './vendor-image-suchaz-dialog.component.html'
})
export class VendorImageSuchazDialogComponent implements OnInit {

    vendorImage: VendorImageSuchaz;
    isSaving: boolean;

    vendors: VendorSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private vendorImageService: VendorImageSuchazService,
        private vendorService: VendorSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.vendorService.query()
            .subscribe((res: HttpResponse<VendorSuchaz[]>) => { this.vendors = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.vendorImage, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.vendorImage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.vendorImageService.update(this.vendorImage));
        } else {
            this.subscribeToSaveResponse(
                this.vendorImageService.create(this.vendorImage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<VendorImageSuchaz>>) {
        result.subscribe((res: HttpResponse<VendorImageSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: VendorImageSuchaz) {
        this.eventManager.broadcast({ name: 'vendorImageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackVendorById(index: number, item: VendorSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-vendor-image-suchaz-popup',
    template: ''
})
export class VendorImageSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vendorImagePopupService: VendorImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.vendorImagePopupService
                    .open(VendorImageSuchazDialogComponent as Component, params['id']);
            } else {
                this.vendorImagePopupService
                    .open(VendorImageSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
