import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { HobbyImageSuchaz } from './hobby-image-suchaz.model';
import { HobbyImageSuchazPopupService } from './hobby-image-suchaz-popup.service';
import { HobbyImageSuchazService } from './hobby-image-suchaz.service';
import { HobbySuchaz, HobbySuchazService } from '../hobby-suchaz';

@Component({
    selector: 'jhi-hobby-image-suchaz-dialog',
    templateUrl: './hobby-image-suchaz-dialog.component.html'
})
export class HobbyImageSuchazDialogComponent implements OnInit {

    hobbyImage: HobbyImageSuchaz;
    isSaving: boolean;

    hobbies: HobbySuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private hobbyImageService: HobbyImageSuchazService,
        private hobbyService: HobbySuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.hobbyService.query()
            .subscribe((res: HttpResponse<HobbySuchaz[]>) => { this.hobbies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.hobbyImage, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.hobbyImage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.hobbyImageService.update(this.hobbyImage));
        } else {
            this.subscribeToSaveResponse(
                this.hobbyImageService.create(this.hobbyImage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<HobbyImageSuchaz>>) {
        result.subscribe((res: HttpResponse<HobbyImageSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: HobbyImageSuchaz) {
        this.eventManager.broadcast({ name: 'hobbyImageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackHobbyById(index: number, item: HobbySuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-hobby-image-suchaz-popup',
    template: ''
})
export class HobbyImageSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private hobbyImagePopupService: HobbyImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.hobbyImagePopupService
                    .open(HobbyImageSuchazDialogComponent as Component, params['id']);
            } else {
                this.hobbyImagePopupService
                    .open(HobbyImageSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
