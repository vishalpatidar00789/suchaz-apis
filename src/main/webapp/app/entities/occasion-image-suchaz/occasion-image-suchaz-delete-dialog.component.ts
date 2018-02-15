import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OccasionImageSuchaz } from './occasion-image-suchaz.model';
import { OccasionImageSuchazPopupService } from './occasion-image-suchaz-popup.service';
import { OccasionImageSuchazService } from './occasion-image-suchaz.service';

@Component({
    selector: 'jhi-occasion-image-suchaz-delete-dialog',
    templateUrl: './occasion-image-suchaz-delete-dialog.component.html'
})
export class OccasionImageSuchazDeleteDialogComponent {

    occasionImage: OccasionImageSuchaz;

    constructor(
        private occasionImageService: OccasionImageSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.occasionImageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'occasionImageListModification',
                content: 'Deleted an occasionImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-occasion-image-suchaz-delete-popup',
    template: ''
})
export class OccasionImageSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private occasionImagePopupService: OccasionImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.occasionImagePopupService
                .open(OccasionImageSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
