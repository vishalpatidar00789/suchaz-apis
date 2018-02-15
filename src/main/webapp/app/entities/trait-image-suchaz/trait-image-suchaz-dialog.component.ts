import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { TraitImageSuchaz } from './trait-image-suchaz.model';
import { TraitImageSuchazPopupService } from './trait-image-suchaz-popup.service';
import { TraitImageSuchazService } from './trait-image-suchaz.service';
import { TraitSuchaz, TraitSuchazService } from '../trait-suchaz';

@Component({
    selector: 'jhi-trait-image-suchaz-dialog',
    templateUrl: './trait-image-suchaz-dialog.component.html'
})
export class TraitImageSuchazDialogComponent implements OnInit {

    traitImage: TraitImageSuchaz;
    isSaving: boolean;

    traits: TraitSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private traitImageService: TraitImageSuchazService,
        private traitService: TraitSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.traitService.query()
            .subscribe((res: HttpResponse<TraitSuchaz[]>) => { this.traits = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.traitImage, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.traitImage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.traitImageService.update(this.traitImage));
        } else {
            this.subscribeToSaveResponse(
                this.traitImageService.create(this.traitImage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TraitImageSuchaz>>) {
        result.subscribe((res: HttpResponse<TraitImageSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TraitImageSuchaz) {
        this.eventManager.broadcast({ name: 'traitImageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTraitById(index: number, item: TraitSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-trait-image-suchaz-popup',
    template: ''
})
export class TraitImageSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traitImagePopupService: TraitImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.traitImagePopupService
                    .open(TraitImageSuchazDialogComponent as Component, params['id']);
            } else {
                this.traitImagePopupService
                    .open(TraitImageSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
