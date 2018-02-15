import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { OccasionImageSuchaz } from './occasion-image-suchaz.model';
import { OccasionImageSuchazPopupService } from './occasion-image-suchaz-popup.service';
import { OccasionImageSuchazService } from './occasion-image-suchaz.service';
import { OccassionSuchaz, OccassionSuchazService } from '../occassion-suchaz';

@Component({
    selector: 'jhi-occasion-image-suchaz-dialog',
    templateUrl: './occasion-image-suchaz-dialog.component.html'
})
export class OccasionImageSuchazDialogComponent implements OnInit {

    occasionImage: OccasionImageSuchaz;
    isSaving: boolean;

    occassions: OccassionSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private occasionImageService: OccasionImageSuchazService,
        private occassionService: OccassionSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.occassionService.query()
            .subscribe((res: HttpResponse<OccassionSuchaz[]>) => { this.occassions = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.occasionImage, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.occasionImage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.occasionImageService.update(this.occasionImage));
        } else {
            this.subscribeToSaveResponse(
                this.occasionImageService.create(this.occasionImage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OccasionImageSuchaz>>) {
        result.subscribe((res: HttpResponse<OccasionImageSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: OccasionImageSuchaz) {
        this.eventManager.broadcast({ name: 'occasionImageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackOccassionById(index: number, item: OccassionSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-occasion-image-suchaz-popup',
    template: ''
})
export class OccasionImageSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private occasionImagePopupService: OccasionImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.occasionImagePopupService
                    .open(OccasionImageSuchazDialogComponent as Component, params['id']);
            } else {
                this.occasionImagePopupService
                    .open(OccasionImageSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
