/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { HobbyImageSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz-delete-dialog.component';
import { HobbyImageSuchazService } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz.service';

describe('Component Tests', () => {

    describe('HobbyImageSuchaz Management Delete Component', () => {
        let comp: HobbyImageSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<HobbyImageSuchazDeleteDialogComponent>;
        let service: HobbyImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [HobbyImageSuchazDeleteDialogComponent],
                providers: [
                    HobbyImageSuchazService
                ]
            })
            .overrideTemplate(HobbyImageSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(HobbyImageSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HobbyImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
